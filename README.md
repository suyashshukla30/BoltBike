# BoltBike
_A bike renting app_

## Follow below pattern when making classes(please 😊)
`
- core/
    - SampleData.kt

- data/                         <-- Data Layer (Remote, Local, Repository)
    - remote/
        - ApiClient.kt
    - local/                    <-- ✅ Put Room DB classes here
        - AppDatabase.kt
        - Entity.kt
        - Dao.kt
    - repository/
        - NoteRepositoryImpl.kt

- domain/                       <-- Business Logic Layer
    - model/
        - Note.kt              <-- Pure data class (no Room annotations)
    - repository/
        - NoteRepository.kt    <-- Interface for repository

- presentation/                <-- UI Layer
    - ui/
        - Screen1.kt
        - Screen2.kt
    - views/
        - Activity1.kt
    - viewmodel/
        - ViewModel1.kt
`