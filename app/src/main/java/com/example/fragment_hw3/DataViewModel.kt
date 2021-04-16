package com.example.fragment_hw3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
    private val dataForSecondFragment = MutableLiveData<Int>()
    private val dataForThirdFragment = MutableLiveData<Int>()
    private val switchFragmentData = MutableLiveData<Boolean>()
    private val listText = MutableLiveData<String>()

    fun getDataForSecondFragment(): MutableLiveData<Int> {
        return dataForSecondFragment
    }

    fun getDataForThirdFragment(): MutableLiveData<Int> {
        return dataForThirdFragment
    }

    fun switchFragments(): MutableLiveData<Boolean> {
        return switchFragmentData
    }

    fun getListText(): MutableLiveData<String> {
        return listText
    }

    fun loadColors() {
        dataForSecondFragment.postValue(generateColor())
        dataForThirdFragment.postValue(generateColor())
    }

    fun loadSwitchState() {
        val oldValue = switchFragmentData.value ?: false
        switchFragmentData.postValue(!oldValue)
    }

    fun loadListText(text: String) {
        listText.postValue(text)
    }
}