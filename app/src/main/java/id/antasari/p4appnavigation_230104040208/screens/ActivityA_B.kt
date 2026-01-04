package id.antasari.p4appnavigation_230104040208.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// ==================== Activity A ====================
@Composable
fun ActivityAScreen(onOpen: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = "Intent Demo",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(Modifier.height(8.dp))
                Text("This screen demonstrates launching a new Activity using an explicit Intent.")
                Spacer(Modifier.height(16.dp))

                // Kode pseudo untuk penjelasan
                CodeBlock(
                    """
                    val intent = Intent(this, ActivityB::class.java)
                    startActivity(intent)
                    """.trimIndent()
                )

                Spacer(Modifier.height(16.dp))
                Button(onClick = onOpen) {
                    Icon(Icons.Outlined.OpenInNew, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Open Detail (via Intent)")
                }
            }
        }

        InfoCard(
            title = "Learning Points",
            bullets = listOf(
                "Explicit Intents specify the target component directly",
                "Use startActivity() to launch the new activity",
                "The new activity is added to the back stack"
            )
        )
    }
}

// ==================== Activity B ====================
@Composable
fun ActivityBScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AssistChip(
            onClick = { },
            label = { Text("Activity B - Success!") }
        )

        Card {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("This activity was successfully launched from Activity A using an Intent.")
                CodeBlock(
                    """
                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                        // Activity B is now running
                    }
                    """.trimIndent()
                )
            }
        }

        InfoCard(
            title = "Activity Lifecycle",
            bullets = listOf("onCreate()", "onStart()", "onResume()")
        )
    }
}

// ==================== Komponen Reusable ====================
@Composable
private fun CodeBlock(text: String) {
    Surface(
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun InfoCard(title: String, bullets: List<String>) {
    Card {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            bullets.forEach { Text("• $it") }
        }
    }
}
