package com.maverick.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.maverick.cleanarchitecturenoteapp.feature_note.data.data_source.NoteDatabase
import com.maverick.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.maverick.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.maverick.cleanarchitecturenoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.maverick.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.maverick.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNoteUseCase
import com.maverick.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.maverick.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotesRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }

}