# pagantis
# Model in which you gonna operate: Banks have customers. Customers have accounts.
Accounts hold money. Transfers are done between accounts. Account holds a list of all
transfers.
There can be two types of transfers:
● Intra-bank transfers, between accounts of the same bank. They don't have commissions,
they don't have limits and they always succeed.
● Inter-bank transfers, between accounts in different banks. They have 5€ commissions,
they have a limit of 1000€ per transfer and they have a 30% chance of failure.

## Documentation about application process
### Part 1
Data structurer is defined by the two files that are included DataStructures.sql and the UML file BankTransferDiagram.pdf.

### Part 2
    This is a rest api application created with java and Spring-boot. The structure is designed by
    layer(api, dao, model, service).
    We used for our needs the below uris :
        * Transfer money from account A to account B,
            > POST : http://localhost:8080/account/1/transfers
            e.g     {
        				"transferType": {
            						"name": "INTER"
        				},
        				"sourceAccountNumber": "GR12345467898900",
        				"targetAccountNumber": "GRTH9878998766",
        				"amount": 500
    				}
        * Get the list of transfers by Account
            > GET : http://localhost:8080/account/1/transfers
        * List of Customers by bank
            > http://localhost:8080/bank/3/customers

    Repositories designed to cover the database transactions (retrieve and store information about movies and directors).
    Enums and exceptions ackages contains some helper classes like enums etc..


## Use of application
### Tools you need to run the application
    1. No need to install a DB as we used for our perposes H2 in memory database
    2. Install Maven 3 (apache-maven-3.6.1)
    3. Install jdk1.8.0_211(Java 8)


## Package and run it.
1. Clone the directory from Github by git clone https://github.com/evasilioni/pagantis.git
2. Go to project directory (pagantis-finance)
3. Run using cmd or git bach : " mvn package "
4. Run " mvn clean install -DskipTests=true "
5. Run the application FinanceApp : " mvn spring-boot:run "
6. Run the tests : " mvn test " 


