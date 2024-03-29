package com.maverick.cleanarchitecturenoteapp.feature_note.domain.use_case

data class NotesUseCases(
    val getNotes: GetNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)