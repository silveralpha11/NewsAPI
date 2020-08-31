package com.adith.smash.x.newsapi

import android.content.Intent
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.adith.smash.x.newsapi.databinding.ActivityDetailBinding
import com.adith.smash.x.newsapi.databinding.CdvNewsHeadlineBinding
import com.adith.smash.x.newsapi.model.ArticlesItem

class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlineVH>(){

    //untuk mengambil data di dalam model article item
    private val listData = ArrayList<ArticlesItem>()

    //fungsi ini berfungsi untuk add data ke dalam recyclerview
    fun addData(items: List<ArticlesItem>){
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    //berfungsi untuk menginflate atau menerapkan operasi yang dibuat kedalam layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlineVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsHeadlineVH(binding)

    }

    //digunakan untuk engetahui panjang/banyak data(size) guna kebutuhan looping
    override fun getItemCount(): Int {
        return listData.size

    }

    //digunakan untuk memposisikan widget pada layout model
    override fun onBindViewHolder(holder: CdvNewsHeadlineVH, position: Int) {
        holder.bind(listData[position])


    }
}

//sebagai adapter recyclerview
class CdvNewsHeadlineVH(private val binding: CdvNewsHeadlineBinding) :
    RecyclerView.ViewHolder(binding.root){
    fun bind(article: ArticlesItem){
        binding.run {
            txtTitle.text = cropText(article.title?: "tidak ada judul")
            imgHeadline.apply {
                load(article.urlToImage){
                scale(Scale.FILL)
                }
                contentDescription = article.description
            }
            //untuk melakukan aksi klik pada gambar
            root.setOnClickListener{
                val intent = Intent(it.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.DETAIL_NEWS, article)//DETAIL_NEWS BERFUNGSI SEBAGAI VARIABEL YANG AKAN DIKIRIMKAN KE DETAIL ACTIVITY
                }

                it.context.startActivity(intent)
            }
        }
    }
        //untuk memotong text yang lebih dari 50
        private fun cropText(text: String): String {
            return text.take(50) + if (text.length > 50)"..." else ""
        }
}
