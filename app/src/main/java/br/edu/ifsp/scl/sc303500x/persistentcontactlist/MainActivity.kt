package br.edu.ifsp.scl.sc303500x.persistentcontactlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.edu.ifsp.scl.sc303500x.persistentcontactlist.model.Contact
import br.edu.ifsp.scl.sc303500x.persistentcontactlist.model.Operation
import br.edu.ifsp.scl.sc303500x.persistentcontactlist.navigation.MainNavHost
import br.edu.ifsp.scl.sc303500x.persistentcontactlist.navigation.Screen
import br.edu.ifsp.scl.sc303500x.persistentcontactlist.ui.composable.component.MainTopAppBar
import br.edu.ifsp.scl.sc303500x.persistentcontactlist.ui.theme.PersistentContactListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()

            val contactViewModel: ContactViewModel = viewModel()

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val showActions = navBackStackEntry?.destination?.route == Screen.List.route

            PersistentContactListTheme {
                Scaffold(
                    topBar = {
                        MainTopAppBar(showActions = showActions) {
                            contactViewModel.updateCurrentContactAndOperation(
                                contact = Contact(),
                                operation = Operation.NEW_OR_EDIT
                            )
                            navHostController.navigate(Screen.Contact.route)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainNavHost(
                        navHostController = navHostController,
                        contactViewModel = contactViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}