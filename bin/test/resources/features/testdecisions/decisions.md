TESTOBJECT A

```json
{
    "id" : 1,
    "name" : "Y4OJ5b\"l/",
    "department" : {
        "id" : 1,
        "name" : "D2g\"bj8yz2Mfpv56nGAAZlyd7cB41Giel@sakRFDGbFr"
    },
    "salary" : 0,
    "employeeStatus" : "ACTIVE",
    "joiningDate" : "2024-05-08"
}
```

RISK


| id       | name | departmentId | departmentName | salary | employeeStatus | joiningdate |
| -------- | ---- | ------------ | -------------- | ------ | -------------- | ----------- |
| AutoIncr | *    | ***          | ***            | ***    | ***            | *           |

CONDITIONS


| id       | name                | departmentId | departmentName      | salary | employeeStatus | joiningdate |
| -------- | ------------------- | ------------ | ------------------- | ------ | -------------- | ----------- |
| AutoIncr | Data !NULL or EMPTY | AutoIncr     | Data !NULL or EMPTY | 3500   | ACTIVE         | =>TODAY     |
| AutoIncr | Data !NULL or EMPTY | AutoIncr     | Data !NULL or EMPTY | 0=>    | ON_LEAVE       | =>TODAY     |
| AutoIncr | Data !NULL or EMPTY | AutoIncr     | Data !NULL or EMPTY |        | TERMINATED     | => TODAY    |

TEST CONDITIONS

* Create

  * Invalid employeeStatus
  * Salary  may not be negative
  * Salary cannot being updated lower then 0
  * Joiningdate in the future is not possible
  * Two or more employees can being added to the same department
  * Create the same employee two times in different departments with different salaries
* Update

  * Name can being updated
  * Deparment name can being updated
  * Salary can being updated but not lower then 0
  * Joiningdata can being updated but not to the past
* Delete

  * A non existing employee cannot being removed
  * A deparmtent with a connection with an employee cannot being removed


| Coverage group             | Intensity: ●  (low) | Intensity: ●●  (medium) | Intensity: ●●●  (high) |
| -------------------------- | -------------------- | ------------------------- | ------------------------- |
| Process-orientedtesting    |                      |                           |                           |
| Condition-orientedtesting  |                      |                           |                           |
| Data-orientedtesting       |                      |                           |                           |
| Appearance-orientedtesting |                      |                           |                           |

TESTING INPUT VALIDATION

- **Escape sequences:** Test with a string that contains escape sequences (e.g., "\n", "\t").
- **Empty string:** Test with an empty string ("").
- **Single character:** Test with a string that contains a single character (e.g., "a").
- **Long string:** Test with a string that is longer than the maximum allowed length.
- **Case sensitivity:** Test with a string in all lowercase, all uppercase, and mixed case to check if the system is case sensitive.
- **Leading and trailing spaces:** Test with a string that has leading and/or trailing spaces (e.g., " abc ").
- **Null character:** Test with a string that contains a null character (e.g., "\0").
- **Control characters:** Test with a string that contains control characters (e.g., ASCII 0-31 and 127).
- **Non-printable characters:** Test with a string that contains non-printable characters.
- **Punctuation:** Test with a string that contains punctuation characters (e.g., ".,;:!?").
- **Accented characters:** Test with a string that contains accented characters (e.g., "éèêë").
- **Surrogate pairs:** Test with a string that contains surrogate pairs (used for encoding Unicode characters outside the Basic Multilingual Plane).
- **Right-to-left characters:** Test with a string that contains right-to-left characters (e.g., Arabic or Hebrew characters).

TESTING THE PROCES

* Changing all values but

TESTOBJECT B

```json
{
    "employeeName": "xW4/VN.zXmfvlK/5gncBBVIHi11Tl.4n4xHTpYhW1\"6\"i25OZtn89m7YXo0T8POhB8T@t'Jpje9nJSJotPrNgT3LXQW.lurmZL",
    "departmentName": "91ae3hN",
    "employeeStatus": "ACTIVE",
    "salaryRange": {
        "from": 0,
        "to": 0
    },
    "joiningDateRange": {
        "from": "2024-05-17",
        "to": "2024-05-17"
    }
}
```

CONDITIONS


| employeeName | departmentName      | employeeStatus | salaryRangeFrom                                                                        | salaryRangeTo | joiningDateFrom | joiningDateTo |
| ------------ | ------------------- | -------------- | -------------------------------------------------------------------------------------- | ------------- | --------------- | ------------- |
| AutoIncr     | Data !NULL or EMPTY | ACTIVE         | =>0 AND !0 AND !0 AND !0 AND !0 AND !0 AND !0 AND !0 AND !0 AND !0 AND !0 AND !0 AND ! |               |                 |               |

RISK


| employeeName | departmentName | employeeStatus | salaryRangeFrom | salaryRangeTo | joiningDateFrom | JoiningDateTo |
| ------------ | -------------- | -------------- | --------------- | ------------- | --------------- | ------------- |
| AutoIncr     | *              | **             | ***             | ***           | ***             | ***           |

TEST CONDITIONS

* search department name not null or empty should return all employees in that department
* employee status must return only a known status and should return all employees
* salaryrangeFrom may not be bigger then salary rangeTo and should only return results in this range and all employees in that range
* SalaryrangeTo may not be smaller then salaryRangeFrom and should return all employees in that range
* joiningDateFrom may note being bigger then joiningdateTo and should return all employees in that range
* JoiningDateTo may not being smaller then joingingdateFrom and should return all employees in that range
* if the salaryrange is met but the joiningdate is not met then it should not return anything
* if the joiningdate is met but the salarydate is not met then it should not return any value
