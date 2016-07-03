package rharriso.kotlinagain

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


data class User(val email: String, val name: String)
data class Bookmark(val url: String)

val mapper = jacksonObjectMapper()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        var tapMe = findViewById(R.id.tapMe) as Button
        tapMe.setOnClickListener({
            run {
                showTheStuff()
            }
        })

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun showTheStuff(){
        var u = mapper.readValue("""{
        "email": "rtharrison86@gmail.com",
        "name": "Ross Harrison"
    }""", User::class.java)
        var b = mapper.readValue("""{
        "url": "http://www.nytimes.com/"
    }""", Bookmark::class.java)

        Log.d("KotlinAndroid", u.toString())
        Log.d("KotlinAndroid", b.toString())
        Log.d("KotlinAndroid", mapper.writeValueAsString(u))
        Log.d("KotlinAndroid", mapper.writeValueAsString(b))

        var userOut = findViewById(R.id.userOut) as TextView
        userOut.text = mapper.writeValueAsString(u);
        var bookmarkOut = findViewById(R.id.bookmarkOut) as TextView
        bookmarkOut.text = mapper.writeValueAsString(b);

    }
}
