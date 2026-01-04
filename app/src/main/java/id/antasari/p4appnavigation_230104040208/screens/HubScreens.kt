package id.antasari.p4appnavigation_230104040208.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import id.antasari.p4appnavigation_230104040208.nav.Route

// 🔹 Kelas tab dasar
sealed class HubTab(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    data object Dashboard : HubTab("Dashboard", Icons.Outlined.Dashboard)
    data object Messages : HubTab("Messages", Icons.Outlined.Chat)
    data object Profile : HubTab("Profile", Icons.Outlined.AccountCircle)
}

@Composable
fun HubScreen(nav: NavHostController, tab: HubTab) {
    val selectedTab = tab

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab is HubTab.Dashboard,
                    onClick = {
                        nav.navigate(Route.HubDashboard.path) {
                            popUpTo(Route.Hub.path) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(HubTab.Dashboard.icon, null) },
                    label = { Text(HubTab.Dashboard.label) }
                )
                NavigationBarItem(
                    selected = selectedTab is HubTab.Messages,
                    onClick = {
                        nav.navigate(Route.HubMessages.path) {
                            popUpTo(Route.Hub.path) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(HubTab.Messages.icon, null) },
                    label = { Text(HubTab.Messages.label) }
                )
                NavigationBarItem(
                    selected = selectedTab is HubTab.Profile,
                    onClick = {
                        nav.navigate(Route.HubProfile.path) {
                            popUpTo(Route.Hub.path) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(HubTab.Profile.icon, null) },
                    label = { Text(HubTab.Profile.label) }
                )
            }
        }
    ) { padding ->
        when (tab) {
            is HubTab.Dashboard -> DashboardTab(Modifier.padding(padding))
            is HubTab.Messages -> MessagesTab(Modifier.padding(padding)) {
                nav.navigate(Route.HubMsgDetail.path)
            }
            is HubTab.Profile -> ProfileTab(Modifier.padding(padding))
        }
    }
}

/* ----- TAB DASHBOARD ----- */
@Composable
private fun DashboardTab(mod: Modifier) {
    Column(mod.fillMaxSize().padding(16.dp),verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Dashboard Fragment", style = MaterialTheme.typography.titleMedium)
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Welcome!", fontWeight = FontWeight.SemiBold)
                Text("This screen represents a dashboard inside a single Activity hosting multiple fragments/tabs.")
            }
        }
        InfoCard(
            "Hints", listOf(
                "Each tab maps to a fragment-like screen",
                "Bottom navigation switches tabs within the same Activity",
                "Back preserves tab state unless the stack is cleared"
            ))
    }
}

/* ----- TAB MESSAGES ----- */
@Composable
private fun MessagesTab(mod: Modifier, onOpenDetail: () -> Unit) {
    Column(mod.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Messages Fragment", style = MaterialTheme.typography.titleMedium)

        // Item 1
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().clickable { onOpenDetail() }
        ) {
            ListItem(
                leadingContent = { Icon(Icons.Outlined.Chat, null) },
                headlineContent = { Text("Android System") },
                supportingContent = { Text("Welcome to Navigation Lab! Tap to open message details.") },
                trailingContent = { Text("2m") }
            )
        }
        // Item 2
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().clickable { onOpenDetail() }
        ) {
            ListItem(
                leadingContent = { Icon(Icons.Outlined.Chat, null) },
                headlineContent = { Text("Compose Tips") },
                supportingContent = { Text("Use Scaffold with TopAppBar and NavigationBar for app structure.") },
                trailingContent = { Text("1h") }
            )
        }
        // Item 3
        ElevatedCard(
            modifier = Modifier.fillMaxWidth().clickable { onOpenDetail() }
        ) {
            ListItem(
                leadingContent = { Icon(Icons.Outlined.Chat, null) },
                headlineContent = { Text("Release Notes") },
                supportingContent = { Text("Material 3 components power modern UI on Android.") },
                trailingContent = { Text("ytd") }
            )
        }
    }
}

/* ----- DETAIL PESAN ----- */
@Composable
fun MessageDetailScreen(onBack: () -> Unit, mod: Modifier = Modifier) {
    Column(mod.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ListItem(
                    leadingContent = { Icon(Icons.Outlined.Chat, null) },
                    headlineContent = { Text("Fragment Navigation") },
                    supportingContent = {
                        Text("This is a detail screen opened from the Messages tab. It mimics fragment-to-fragment navigation inside one Activity.")
                    }
                )
                ElevatedCard {
                    Column(
                        Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("Message Details", style = MaterialTheme.typography.titleSmall)
                        Divider()
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("From", color = MaterialTheme.colorScheme.onSurface.copy(.7f))
                            Text("Android System", fontWeight = FontWeight.SemiBold)
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("From", color = MaterialTheme.colorScheme.onSurface.copy(.7f))
                            Text("Subject: Welcome to Navigation Lab!",
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("From", color = MaterialTheme.colorScheme.onSurface.copy(.7f))
                            Text("Time: 2 minutes ago", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
                Surface(tonalElevation = 2.dp, shape = MaterialTheme.shapes.medium) {
                    Text(
                        """
                        Fragment Transaction transaction =
                            getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new DetailFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        """.trimIndent(),
                        modifier = Modifier.fillMaxWidth().padding(12.dp)
                    )
                }
                OutlinedButton(onClick = onBack, modifier = Modifier.align(Alignment.End)) { Text("Back") }
            }
        }

        InfoCard(
            title = "Fragment Benefits",
            bullets = listOf(
                "Reusable UI components",
                "Independent lifecycle management",
                "Flexible layout arrangements",
                "Better memory management"
            )
        )
    }
}

/* ----- TAB PROFILE ----- */
@Composable
private fun ProfileTab(mod: Modifier) {
    Column(mod.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Profile Fragment", style = MaterialTheme.typography.titleMedium)

        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ElevatedCard {
                    ListItem(
                        leadingContent = { Icon(Icons.Outlined.AccountCircle, null) },
                        headlineContent = { Text("Ruqayah") },
                        supportingContent = { Text("Android Navigation Student") }
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(onClick = {}, label = { Text("Demos Completed") })
                    AssistChip(onClick = {}, label = { Text("3/4") })
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(onClick = {}, label = { Text("Current Level") })
                    AssistChip(onClick = {}, label = { Text("Intermediate") })
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(onClick = {}, label = { Text("Navigation Score") })
                    AssistChip(onClick = {}, label = { Text("85%") })
                }
            }
        }
        AssistChip(
            onClick = {},
            label = { Text("Each tab is a fragment-like screen. The Activity controls back stack & transactions.") }
        )
    }
}

/* ----- KOMPONEN REUSABLE ----- */
@Composable
private fun InfoCard(title: String, bullets: List<String>) {
    Card {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            bullets.forEach { Text("• $it") }
        }
    }
}
