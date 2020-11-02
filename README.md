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