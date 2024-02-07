package fr.hubert.gaming.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.hubert.gaming.databinding.ItemGameBinding
import fr.hubert.gaming.data.entity.Game

class GameListAdapter(private var games: List<Game>) : RecyclerView.Adapter<GameListAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int = games.size

    inner class GameViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {

            binding.textViewTitle.text = game.name
            binding.textViewName.text = game.name
            // Set listeners for edit and delete icons
            binding.imageViewEdit.setOnClickListener {
                // TODO: Implement edit functionality
            }
            binding.imageViewDelete.setOnClickListener {
                // TODO: Implement delete functionality
            }
        }
    }

    // TODO: Add methods for updating the list, handling clicks, etc.
}
