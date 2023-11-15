package com.example.redditapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.redditapp.databinding.FragmentDetailsBinding
import com.example.redditapp.utils.loadImage

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        with(binding) {
            subreddit.text = args.item.data.subreddit
            title.text = "Title: ${args.item.data.title}"
            comments.text = "Number of comments: ${args.item.data.num_comments}"
            subscribers.text = "Subreddit Subscribers: ${args.item.data.subreddit_subscribers}"
            author.text = "Author: ${args.item.data.author}"
            avatar.loadImage(args.item.data.thumbnail)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}