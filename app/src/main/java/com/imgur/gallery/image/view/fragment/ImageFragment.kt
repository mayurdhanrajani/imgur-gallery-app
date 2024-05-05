package com.imgur.gallery.image.view.fragment

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.imgur.gallery.R
import com.imgur.gallery.databinding.FragmentImageBinding
import com.imgur.gallery.image.model.datamodel.Album
import com.imgur.gallery.image.model.enums.LayoutToggle.GRID
import com.imgur.gallery.image.model.enums.LayoutToggle.LIST
import com.imgur.gallery.image.view.adapter.ImageAdapter
import com.imgur.gallery.image.viewmodel.ImageViewModel
import com.imgur.gallery.networking.internet.CheckInternetConnection
import com.imgur.gallery.utils.Constants.DEFAULT_BOOLEAN_VALUE
import com.imgur.gallery.utils.Constants.EMPTY_STRING
import com.imgur.gallery.utils.Constants.SEARCH_VIEW_TEXT_SIZE
import com.imgur.gallery.utils.Constants.SPAN_COUNT
import dagger.hilt.android.AndroidEntryPoint

/** The fragment shows the UI of ImageFragment list **/
@AndroidEntryPoint
class ImageFragment : Fragment() {

    /** This variable will store the instance of CurrencyRateFragment layout file views **/
    private lateinit var binding: FragmentImageBinding

    /** This variable will store the instance of MenuHost used to show the search and the layout toggle option **/
    private lateinit var menuHost: MenuHost

    /** This variable will store the instance of Menu used to change the layout toggle option **/
    private lateinit var imageMenu: Menu

    /** This variable will store the instance of ImageAdapter used to show the images and other values **/
    private lateinit var imageAdapter: ImageAdapter

    /** This variable will store the instance of album list of PagingData type **/
    private lateinit var albumList: PagingData<Album>

    /** This variable will store the instance of ImageViewModel **/
    private val imageViewModel: ImageViewModel by viewModels()

    /** This variable will store the instance of CheckInternetConnection to check the internet connectivity **/
    private val checkInternetConnection by lazy { CheckInternetConnection(requireContext()) }

    /** This variable will store the value of text entered into SearchView **/
    private var searchQuery = EMPTY_STRING

    /** This variable will store whether the entered text was submitted **/
    private var isQueryTextSubmitted = DEFAULT_BOOLEAN_VALUE

    /** This variable will store the selected LayoutToggle enum value **/
    private var selectedLayoutToggle = LIST

    /** This function will create the view of the layout file defined **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater)
        return binding.root
    }

    /** This function will be called after the view is created and used to initialize the views and values **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    /** This function will start calling the functions to initialize views **/
    private fun initializeViews() {
        createMenu()
        setAdapter()
        setListeners()
        observeLiveData()
    }

    /** This function will create the menu in the toolbar **/
    private fun createMenu() {
        menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                imageMenu = menu
                menuInflater.inflate(R.menu.menu_image, menu)
                setSearchView(menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_grid -> {
                        selectedLayoutToggle = GRID
                        menuItem.isVisible = false
                        imageMenu.findItem(R.id.action_list).isVisible = true
                        binding.rvImages.layoutManager =
                            GridLayoutManager(requireContext(), SPAN_COUNT)
                        setAdapter()
                        submitPagingData()
                    }
                    R.id.action_list -> {
                        selectedLayoutToggle = LIST
                        menuItem.isVisible = false
                        imageMenu.findItem(R.id.action_grid).isVisible = true
                        binding.rvImages.layoutManager = LinearLayoutManager(requireContext())
                        setAdapter()
                        submitPagingData()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    /** This function will set the ImageAdapter with the RecyclerView **/
    private fun setAdapter() {
        imageAdapter = ImageAdapter(requireContext(), selectedLayoutToggle)
        binding.rvImages.adapter = imageAdapter
    }

    /** This function will set the listener for the views used **/
    private fun setListeners() {
        binding.swipeLayout.setOnRefreshListener {
            fetchImages()
            binding.swipeLayout.isRefreshing = false
        }

        imageAdapter.addLoadStateListener {
            updateUIState(imageAdapter.itemCount == 0)
        }
    }

    /** This function will observe the LiveData called in the ViewModel **/
    private fun observeLiveData() {
        checkInternetConnection.observe(viewLifecycleOwner) {
            updateUIState(imageAdapter.itemCount == 0)
            if (it) fetchImages() else showNoInternetDialog()
        }

        imageViewModel.albumList.observe(viewLifecycleOwner) {
            albumList = it
            submitPagingData()
        }
    }

    /** This function will set the SearchView to search for the images from the API **/
    private fun setSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)

        val searchView = searchItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                isQueryTextSubmitted = true
                searchItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (isQueryTextSubmitted) {
                    isQueryTextSubmitted = false
                } else {
                    searchQuery = newText.toString()
                    fetchImages()
                }
                return false
            }
        })

        (searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as EditText).apply {
            setTextColor(ResourcesCompat.getColor(resources, R.color.text_color_four, null))
            setHintTextColor(ResourcesCompat.getColor(resources, R.color.text_color_one, null))
            textSize = SEARCH_VIEW_TEXT_SIZE
            hint = context.getString(R.string.search_image_message)
            typeface = ResourcesCompat.getFont(requireContext(), R.font.font_regular)
        }
    }

    /** This function will update the UI states based on different conditions of Internet, list size and search results **/
    private fun updateUIState(isListEmpty: Boolean = false) {
        binding.emptyView.root.isVisible = isListEmpty
        if (checkInternetConnection.value == false) {
            binding.emptyView.imageView.setImageResource(R.drawable.ic_no_internet)
            binding.emptyView.tvTitle.text = getText(R.string.no_internet)
            binding.emptyView.tvSubtitle.text = getText(R.string.no_internet_message)
        } else if (searchQuery.isEmpty()) {
            binding.emptyView.imageView.setImageResource(R.drawable.ic_no_image)
            binding.emptyView.tvTitle.text = getText(R.string.no_image)
            binding.emptyView.tvSubtitle.text = getText(R.string.empty_search_message)
        } else {
            binding.emptyView.imageView.setImageResource(R.drawable.ic_no_image)
            binding.emptyView.tvTitle.text = getText(R.string.no_image)
            binding.emptyView.tvSubtitle.text = getText(R.string.empty_data_message)
        }
    }

    /** This function will fetch the images from the API **/
    private fun fetchImages() {
        if (searchQuery.isNotEmpty()) {
            imageViewModel.fetchImages(searchQuery)
        }
    }

    /** This function will show the dialog when internet connection is disconnected **/
    private fun showNoInternetDialog() {
        findNavController().navigate(R.id.action_imageFragment_to_noInternetBottomSheetDialogFragment)
    }

    /** This function will send the data to the adapter with the help of pagination **/
    private fun submitPagingData() {
        if (::albumList.isInitialized) {
            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                imageAdapter.submitData(albumList)
            }
        }
    }

}