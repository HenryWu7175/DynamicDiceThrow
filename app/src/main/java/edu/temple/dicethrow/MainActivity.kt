package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show _only_ ButtonFragment if portrait
            - show _both_ fragments if Landscape
          */
        if (savedInstanceState == null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                //this only executes if in portrait and if the activity is being created for the first time
                supportFragmentManager.beginTransaction()
                    .add(R.id.container1, ButtonFragment())
                    .commit()
            } else {
                //this executes if in landscape and if the activity is being created for the first time
                supportFragmentManager.beginTransaction()
                    .add(R.id.container1, ButtonFragment())
                    .add(R.id.container2, DieFragment())
                    .commit()
            }
        }else{
            //if the activity is being recreated after a configuration change, the containers will be corrected
            // to show the correct fragment for landscape mode
           if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
               supportFragmentManager.popBackStack()
           }
        }

    }

    /* TODO 2: switch fragments if die rolled and in portrait (no need to switch fragments if Landscape)
        */


    // This callback function gets invoked when the child Fragment invokes it
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (supportFragmentManager.findFragmentById(R.id.container1) !is DieFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, DieFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }


}