package com.adith.smash.x.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import coil.api.load
import coil.size.Scale
import com.adith.smash.x.newsapi.databinding.ActivityDetailBinding
import com.adith.smash.x.newsapi.model.ArticlesItem

class DetailActivity : AppCompatActivity() {

    //variabel untuk menangkap data yang di kirimkan oleh MainActivity melalui CdvNewsHeadlineAdapter
    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    //untuk menampilkan view, karena kita akan menampilkan detail activity maka yanag di extend ActivityDetailBinding
    //jika yang di extend MainActivity maka yang di extend ActivityBinding
    //intinya tinggal tambahkan tulisan binding di akhir
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detail)

        //untuk menampilkan data yang dikirim oleh MainActivity melalui CdvHeadlineAdapter
        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        //ntuk membuild layout
        binding.run {
            setContentView(root)

            //untuk membuild actionbar
            setSupportActionBar(toolBar)


            //untuk menampilkan tombol back
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            title = data.title
            imgToolbar.apply { load(data.urlToImage){
                scale(Scale.FILL)
                }
                contentDescription = data.description
            }
            //untuk get content
            txtContent.text = data.content

            //untuk get PublishAt
            txtDate.text = data.publishedAt
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
