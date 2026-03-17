package ca.qc.chc.andriond.listdemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun retrieveCountries(): List<CountryModel> {
    //We use remember so the list is created only once during composition
    // and not recreated every time the Composable recomposes.
    return remember {
        listOf(
            CountryModel(1,
                "Argentina",
                "This is the Argentina Flag",
                R.drawable.argentina),
            CountryModel(2,
                "Brazil",
                "This is the Brazil Flag",
                R.drawable.brazil),
            CountryModel(3,
                "Bulgaria",
                "This is the Bulgaria Flag",
                R.drawable.bulgaria),
            CountryModel(4,
                "France",
                "This is the France Flag",
                R.drawable.france),
            CountryModel(5,
                "Germany",
                "This is the Germany Flag",
                R.drawable.germany),
            CountryModel(6,
                "Ireland",
                "This is the Ireland Flag",
                R.drawable.ireland),
            CountryModel(7,
                "Italy",
                "This is the Italy Flag",
                R.drawable.italy),
            CountryModel(8,
                "Netherlands",
                "This is the Netherlands Flag",
                R.drawable.netherlands),
            CountryModel(9,
                "Romania",
                "This is the Romania Flag",
                R.drawable.romania),
            CountryModel(10,
                "Slovakia",
                "This is the Slovakia Flag",
                R.drawable.slovakia),
            CountryModel(11,
                "Spain",
                "This is the Spain Flag",
                R.drawable.spain),
            CountryModel(12,
                "Turkey",
                "This is the Turkey Flag",
                R.drawable.turkey)
        )
    }
}