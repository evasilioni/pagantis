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
Part 1
Define a set of data structures to accurately reflect the described model. Make sure that new
type of transfers can be added with minimal effort.

Part 2
    This is a rest api application created with java and Spring-boot. The structure is designed by
    layer(api, dao, model, service).
    We used for our needs the below uris :
        * Sci-Fi Movies Currently playing in Greece
            > https://api.themoviedb.org/3/discover/movie?api_key=&region=GR&release_date.gte=2019-05-01&release_date.lte=2019-08-01
        * Movie
            > https://api.themoviedb.org/3/movie/429617?api_key=
        * List of Directors
            > https://api.themoviedb.org/3/movie/{movie_id}/credits?
         * Director more information like, imdbId
            > https://api.themoviedb.org/3/person/{person_id}?api_key=

    Services designed to cover the API request to 'https://api.themoviedb.org/3'.
    Repositories designed to cover the database transactions (retrieve and store information about movies and directors).
    Utils packages contains some helper classes like enums etc..
        > Region enumerator created for fixed values regarding the REGION. e.g. GR

## Use of application
### Tools you need to run the application
    1. No need to install a DB as we used for our perposes H2 in memory database
    2. Install Maven 3 (apache-maven-3.6.1)
    3. Install jdk1.8.0_211(Java 8)


## Package and run it.
1. Clone the directory from Gitlab by git clone https://gitlab.com/welcomepickup/themoviedb.git
2. Go to project directory
3. Run using cmd or git bach : " mvn package "
4. Run the application by giving the correct arguments
* $ java -jar target/TheMovieDb-1.0-SNAPSHOT.jar "GR" "2019-05-01" "2019-08-01" * , or
* $ java -jar target/TheMovieDb-1.0-SNAPSHOT.jar "GR" *
  > "GR" -> from Region (GR is the country code) ,
  > "2019-05-01" -> release_date.gte ,
  >  "2019-08-01" -> release_date.lte


