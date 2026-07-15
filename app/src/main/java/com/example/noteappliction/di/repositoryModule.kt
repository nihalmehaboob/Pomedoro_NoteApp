package com.example.noteappliction.di

import com.example.noteappliction.data.repository.NoteRepositoryImpl
import com.example.noteappliction.domain.repository.NoteRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository
}