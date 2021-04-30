# Transactions manager
The simple project, that helps users manage their bank transactions. Application contains two screens
## All transactions screen
This screen represents user total balance at the top of the scren and all user’s transactions, that he commited before

![image](https://user-images.githubusercontent.com/60261468/116677125-7fe53c80-a9b0-11eb-9697-7f98f85eaabc.png)
                                                                                                                            
## Add new transaction
On this screen users can add new transaction to his account. He can choose transaction type (deposit or withdraw) and sum, that he want to withdraw or deposit.

![image](https://user-images.githubusercontent.com/60261468/116677201-98eded80-a9b0-11eb-9eda-df0d138de83d.png)
                                                                                                                                           
If user wants to withdraw amount of money, that is bigger than his balance, he will see toast on screen with message “You do not have enough money!“.

![image](https://user-images.githubusercontent.com/60261468/116677303-bc189d00-a9b0-11eb-831b-7995ee9d9e61.png)


## Architecture
This application uses a clean architecure, which presentation layer based on MVVM. As we don’t have remote data source (api) and local data source (database), all user data is contained in repository. Both screen’s view-models save and get data from repository through use case. All classes instances injects in another classes by dependency injection library (kodein)
## Technologies
Kodein (dependency injection), LiveData, Kotlin Coroutines
