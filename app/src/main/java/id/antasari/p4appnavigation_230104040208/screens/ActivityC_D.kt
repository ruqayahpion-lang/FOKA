package id.antasari.p4appnavigation_230104040208.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ActivityCScreen(onSend: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }

    Column(
      Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Data Input Form",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = nim,
                    onValueChange = { nim = it },
                    label = { Text("Student ID") },
                    leadingIcon = { Icon(Icons.Outlined.CreditCard, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                CodeBlock(
                    """
                    // Konsep Intent (gaya klasik, untuk edukasi):
                    val intent = Intent(this, ActivityD::class.java)
                    intent.putExtra("NAME", name)
                    intent.putExtra("STUDENT_ID", studentId)
                    startActivity(intent)

                    // Di Compose Navigation, kita kirim via argumen rute
                    """.trimIndent()
                )

                Button(
                    onClick = { onSend(name.trim(), nim.trim()) },
                    enabled = name.isNotBlank() && nim.isNotBlank(),
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Send to Detail")
                }
            }
        }

        InfoCard(
            title = "Intent Extras",
            bullets = listOf(
                "Data dikirim sebagai key-value pairs",
                "Mendukung primitif, String, Parcelable",
                "Di Compose Navigation, kita gunakan argumen rute"
            )
        )
    }
}

@Composable
fun ActivityDScreen(name: String, nim: String, onResend: () -> Unit) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Received Data",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                ElevatedCard {
                    ListItem(
                        headlineContent = { Text("Name") },
                        supportingContent = { Text(name) },
                        leadingContent = { Icon(Icons.Outlined.Person, null) }
                    )
                }

                ElevatedCard {
                    ListItem(
                        headlineContent = { Text("Student ID") },
                        supportingContent = { Text(nim) },
                        leadingContent = { Icon(Icons.Outlined.CreditCard, null) }
                    )
                }

                CodeBlock(
                    """
                    // Konsep pengambilan data (gaya klasik):
                    val name = intent.getStringExtra("NAME")
                    val studentId = intent.getStringExtra("STUDENT_ID")

                    // Di Compose Navigation data dibaca dari argumen backStackEntry
                    """.trimIndent()
                )

                OutlinedButton(onClick = onResend) {Text("Resend / Edit") }
            }
        }

        InfoCard(
            title = "Data Flow",
            bullets = listOf(
                "Activity C: user input",
                "Data dikemas (argumen rute)",
                "Activity D: tampilkan hasil"
            )
        )
    }
}

@Composable
private fun CodeBlock(text: String) {
    Surface(tonalElevation = 2.dp, shape = MaterialTheme.shapes.medium) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun InfoCard(title: String, bullets: List<String>) {
    Card {
        Column(
            Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            bullets.forEach { Text("• $it") }
        }
    }
}
