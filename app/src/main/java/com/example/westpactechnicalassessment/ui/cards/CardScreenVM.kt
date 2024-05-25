package com.example.westpactechnicalassessment.ui.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.westpactechnicalassessment.usecase.IGetCardsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardScreenVM @Inject constructor(
    private val getCardsUC: IGetCardsUC
): ViewModel() {

    init {
        viewModelScope.launch {
            getCardsUC()
        }
    }
}