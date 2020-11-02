# Librarian Service

port 8080

- - - -

### /getLibBranches

- GET

- - - -

### /getBooksByBranch?branchId={branchId}

- GET

- - - -

### /updateLib

- POST

#### Request body:

```
{
  "branchId": {branchId},
  "branchName": {newBranchName},
  ...
}
```

- - - -

### /updateNoOfCopies

- POST

#### Request body:

```
{
  "branch": {
    "branchId": {branchId}
  },
  "book": {
    "bookId": {bookId}
  },
  "noOfCopies": {noOfCopies}
}
```

- - - -

### /getNoOfCopies?branchId={branchId}&bookId={bookId}

- GET

# Admin Service

port 8080

- - - -

### /overrideDueDate

- POST

#### Request body:

```
{
  "branch": {
    "branchId": {branchId}
  }, 
  "book": {
    "bookId": {bookId}
  },
  "cardNo": {cardNo},
  "numDaysToExtend": {numDaysToExtend},
}
```

- - - -

### /addBook

- POST

- - - -

### /updateBook

- POST

- - - -

### /deleteBook

- DELETE

- - - -

### /getBooksByQuery?searchSting={searchString}

- GET

- - - -

### /addGenre

- POST

- - - -

### /updateGenre

- POST

- - - -

### /deleteGenre

- DELETE

- - - -

### /getGenres

- GET

- - - -

### /addPublisher

- POST

- - - -

### /updatePublisher

- POST

- - - -

### /deletePublisher

- DELETE

- - - -

### /getPublishers

- GET

- - - -

### /addBranch

- POST

- - - -

### /updateBranch

- POST

- - - -

### /deleteBranch

- DELETE

- - - -

### /getBranches

- GET

- - - -

### /addBorrower

- POST

- - - -

### /updateBorrower

- POST

- - - -

### /deleteBorrower

- DELETE

- - - -

### /getBorrowers

- GET
