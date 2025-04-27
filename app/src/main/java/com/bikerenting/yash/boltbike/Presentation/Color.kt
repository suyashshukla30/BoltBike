package com.bikerenting.yash.boltbike.Presentation

import androidx.compose.ui.graphics.Color

// Primary Colors, cards border when selected(placeholder border too)
val BrightOrange = Color(0xFFFF6F00)
// Primary color for CTAs and buttons
val TextDark = Color(0xFF1A1A1A)

// Light Gray -  cards border when not selected(placeholder border too)
val LightGray = Color(0xFFF5F5F5)

// Soft White Background, Used for cards, input fields.
val BackgroundWhite = Color(0xFFFDFDFD)

// Button text color when primary color is used in background.
val ButtonTextWhite = Color(0xFFFDFDFD)

// Placeholder Text / Secondary Text
val TextSecondary = Color(0xFF808080)

// Placeholder Text / Secondary Text(use only  when, inverse icon, with app name, and user need too feed data)
val TextBlack = Color(0xFF000000)

// Icon and Divider Gray
val DividerGray = Color(0xFFE0E0E0)

// Accent Navy (used in logo text or potential dark mode)
val NavyAccent = Color(0xFF1E3A8A)

// Success Green (e.g., tags like "Available")
val SuccessGreen = Color(0xFF4CAF50)

// Error Red (optional use)
val ErrorRed = Color(0xFFD32F2F)

// Additional Theme Colors (Existing)
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650A4)
val PurpleGrey40 = Color(0xFF625B71)
val Pink40 = Color(0xFF7D5260)


// --- Typography Specific Colors (🔠 Headings, Subheadings, Body Text, Muted Text) ---
val HeadingMain = Color(0xFF333333)    // Navy Blue (#1E3A8A) - Main Headings (H1, Page Titles)
val Subheading = NavyAccent    // Subheadings (H2, Sections)
val BodyText = Color(0xFF000000)        // Black (#000000) - Body Text (General Content)
val MutedText = Color(0xFF777777)       // Gray (#777777) - Muted Text (Placeholders, Labels, Descriptions)







/*Here’s a structured way to apply your **RideOn color scheme** across different UI elements:

---

### **🚀 1. Background Colors**
| UI Element  | Color  | Reason |
|-------------|--------|--------|
| **Main App Background** | **Light Gray (#F5F5F5)** | Keeps the UI clean and minimal. Avoids too much brightness. |
| **Login & Payment Screens** | **Navy Blue (#1E3A8A)** | Gives a premium and professional look. |
| **Splash Screen** | **Bright Orange (#FF6F00)** | Strong brand presence and eye-catching. |

---

### **🎴 2. CardView & Containers**
| UI Element  | Color  | Reason |
|-------------|--------|--------|
| **General Cards (Bike Listings, Booking Info, etc.)** | **White (#FFFFFF)** with **Light Gray Borders (#E0E0E0)** | Ensures readability and separation from the background. |
| **Selected Card / Active Item** | **Light Gray (#F5F5F5)** with **Orange Border (#FF6F00)** | Highlights selected items while keeping it subtle. |

---

### **🔠 3. Typography (Headings, Subheadings, Text)**
| UI Element  | Color  | Reason |
|-------------|--------|--------|
| **Main Headings (H1, Page Titles)** | **Navy Blue (#1E3A8A)** | Professional and bold without being overwhelming. |
| **Subheadings (H2, Sections)** | **Dark Gray (#333333)** | Good contrast, maintains readability. |
| **Body Text (General Content)** | **Black (#000000)** | Clear and easy to read. |
| **Muted Text (Placeholders, Labels, Descriptions)** | **Gray (#777777)** | Keeps input fields and secondary text subtle. |

---

### **🎯 4. Buttons & Interactive Elements**
| UI Element  | Color  | Reason |
|-------------|--------|--------|
| **Primary Buttons ("Rent Now", "Login")** | **Bright Orange (#FF6F00)** | Eye-catching, grabs attention. |
| **Secondary Buttons ("Cancel", "View More")** | **Navy Blue (#1E3A8A)** | Provides contrast while staying professional. |
| **Disabled Buttons** | **Light Gray (#E0E0E0) with Gray Text** | Indicates inactive state. |
| **Text on Primary Buttons** | **White (#FFFFFF)** | High contrast for readability. |

---

### **🔲 5. Icons & Status Indicators**
| UI Element  | Color  | Reason |
|-------------|--------|--------|
| **Active Icons (Navigation, Selected Items)** | **Bright Orange (#FF6F00)** | Highlights active items. |
| **Inactive Icons** | **Dark Gray (#666666)** | Keeps the UI minimal. |
| **Success Indicators (Completed Booking, Payment Success)** | **Green (#4CAF50)** | Universally represents success. |
| **Error / Warning Messages** | **Red (#D32F2F)** | Alerts the user clearly. |

---

## **🎨 Final Summary (Where to Use Which Color)**
- **🟠 Bright Orange (#FF6F00)** → CTA Buttons, Highlights, Active Icons, Splash Screen
- **🔵 Navy Blue (#1E3A8A)** → App Bar, Navigation, Payment Screens, Secondary Buttons
- **⚪ Light Gray (#F5F5F5)** → Background, Dividers, Input Fields
- **⚫ Black / Dark Gray (#333333 - #777777)** → Text, Subheadings, Placeholders
- **🟢 Green (#4CAF50) & 🔴 Red (#D32F2F)** → Success & Error States

---
*/