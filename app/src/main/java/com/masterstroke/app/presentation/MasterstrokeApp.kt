package com.masterstroke.app.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

private enum class Destination(val route: String, val label: String, val symbol: String) {
    Home("home", "Home", "⌂"), Attendance("attendance", "Attendance", "✓"),
    Sites("sites", "Sites", "⌖"), Documents("documents", "Documents", "▤"), Profile("profile", "Profile", "●")
}

@Composable
fun MasterstrokeApp(viewModel: AppViewModel = viewModel()) {
    val session by viewModel.session.collectAsStateWithLifecycle()
    if (session == null) LoginScreen(onContinue = viewModel::enterPreview)
    else MainNavigation(session!!.displayName, session!!.roleLabel, viewModel::signOut)
}

@Composable
private fun LoginScreen(onContinue: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(32.dp), Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Masterstroke", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
        Text("Field operations", style = MaterialTheme.typography.titleMedium)
        Text("Secure sign-in will be connected to the Masterstroke backend.", modifier = Modifier.padding(top = 12.dp, bottom = 28.dp))
        Button(onClick = onContinue, modifier = Modifier.fillMaxWidth()) { Text("Enter preview") }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainNavigation(name: String, role: String, onSignOut: () -> Unit) {
    val navController = rememberNavController()
    val current by navController.currentBackStackEntryAsState()
    val selectedRoute = current?.destination?.route ?: Destination.Home.route
    val items = Destination.entries
    Scaffold(
        topBar = { TopAppBar(title = { Text("Masterstroke") }) },
        bottomBar = { NavigationBar { items.forEach { item -> NavigationBarItem(selected = selectedRoute == item.route, onClick = { navController.navigate(item.route) { popUpTo(navController.graph.findStartDestination().id) { saveState = true }; launchSingleTop = true; restoreState = true } }, icon = { Text(item.symbol) }, label = { Text(item.label) }) } } },
    ) { padding ->
        NavHost(navController, Destination.Home.route, Modifier.padding(padding)) {
            composable(Destination.Home.route) { HomeScreen(name, role) }
            composable(Destination.Attendance.route) { PlaceholderScreen("Attendance", "Daily attendance capture will be added after the approved process and sheets are reviewed.") }
            composable(Destination.Sites.route) { PlaceholderScreen("Sites", "Assigned-site information will appear here when the backend contract is defined.") }
            composable(Destination.Documents.route) { PlaceholderScreen("Documents", "Document review and OCR-assisted workflows are planned; OCR results will always require human review.") }
            composable(Destination.Profile.route) { ProfileScreen(name, role, onSignOut) }
        }
    }
}

@Composable
private fun HomeScreen(name: String, role: String) = Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
    Text("Hello, $name", style = MaterialTheme.typography.headlineMedium)
    Text(role, color = MaterialTheme.colorScheme.secondary)
    InfoCard("Today’s operations", "Attendance and site updates will be available here once workflows are approved.")
    InfoCard("Offline-ready foundation", "Future field actions can be saved locally and synchronized safely when connectivity returns.")
}

@Composable
private fun PlaceholderScreen(title: String, description: String) = Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center) {
    Text(title, style = MaterialTheme.typography.headlineMedium)
    Text(description, modifier = Modifier.padding(top = 12.dp), style = MaterialTheme.typography.bodyLarge)
}

@Composable
private fun ProfileScreen(name: String, role: String, onSignOut: () -> Unit) = Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
    Text("Profile", style = MaterialTheme.typography.headlineMedium)
    Text(name, style = MaterialTheme.typography.titleLarge)
    Text(role)
    Button(onClick = onSignOut, modifier = Modifier.padding(top = 16.dp)) { Text("Sign out") }
}

@Composable
private fun InfoCard(title: String, body: String) = Card(Modifier.fillMaxWidth()) { Column(Modifier.padding(20.dp)) { Text(title, style = MaterialTheme.typography.titleMedium); Text(body, modifier = Modifier.padding(top = 6.dp)) } }
