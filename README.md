# Transactions manager
The simple project, that helps users manage their bank transactions. Application contains two screens
## All transactions screen
This screen represents user total balance at the top of the scren and all user’s transactions, that he commited before
<img src=“https://user-images.githubusercontent.com/60261468/116666129-49ed8b80-a9a3-11eb-9657-0e105a68bff8.png” width=“250" height = “450”>
## Add new transaction
On this screen users can add new transaction to his account. He can choose transaction type (deposit or withdraw) and sum, that he want to withdraw or deposit.
<img src=“https://user-images.githubusercontent.com/60261468/116666674-e879ec80-a9a3-11eb-8098-44fff0c4121d.png” width=“250” height = “450">
If user wants to withdraw amount of money, that is bigger than his balance, he will see toast on screen with message “You do not have enough money!“.
<img src=“https://user-images.githubusercontent.com/60261468/116667011-57efdc00-a9a4-11eb-98a7-850ad7c3dfc4.png” width=“250" height = “450”>
## Architecture
This application uses a clean architecure, which presentation layer based on MVVM. As we don’t have remote data source (api) and local data source (database), all user data is contained in repository. Both screen’s view-models save and get data from repository through use case. All classes instances injects in another classes by dependency injection library (kodein)
## Technologies
Kodein (dependency injection), LiveData, Kotlin Coroutines
