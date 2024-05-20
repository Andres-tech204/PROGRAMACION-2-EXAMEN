package cl.andvasquez.android.examenprogramacion2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.andvasquez.android.examenprogramacion2.Datos.MViewModel
import cl.andvasquez.android.examenprogramacion2.Datos.Paginas.FormularioMedidas
import cl.andvasquez.android.examenprogramacion2.Datos.Paginas.ListaMedidas

class MainActivity : ComponentActivity() {

    private val MViewModel by viewModels<MViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: MViewModel = viewModel()
            Surface(modifier = Modifier.fillMaxSize()
            ){
                AppCobroJusto(MViewModel)
            }
        }
    }
}
@Preview
@Composable
fun AppCobroJusto(
    vm: MViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            ListaMedidas(
                onClick = {
                    navController.navigate("registro")
                }, vm
            )
        }
        composable("registro") {
            FormularioMedidas(
                onBackClick = { navController.navigate("inicio") }, vm
            )
        }
    }
}