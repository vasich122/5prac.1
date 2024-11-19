package com.example.a4prac

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a4prac.databinding.FragmentDataBaseBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class DataBaseFragment : Fragment() {
    private lateinit var binding: FragmentDataBaseBinding
    private val postsDao: PostsDAO by inject() // Внедрение PostsDAO
    private lateinit var adapter: DatabaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBaseBinding.inflate(inflater, container, false)

        // Инициализация RecyclerView
        adapter = DatabaseAdapter(emptyList())
        binding.titleTextView.layoutManager = LinearLayoutManager(requireContext())
        binding.titleTextView.adapter = adapter

        // Загрузка данных из базы данных
        postsDao.getAllPosts().observe(viewLifecycleOwner) { allPosts ->
            if (allPosts.isNotEmpty()) {
                adapter.updatePosts(allPosts) // Обновление адаптера
            } else {
                binding.bodyTextView.text = "Нет доступных записей"
            }
        }

        return binding.root
    }
}
