# Validation rules:
- Not supported dublicates;
- Not supported empty strings;
- Not supported whitespaces and non-visible characters(e.g., tab, \n).;

# Test requests
###
POST http://localhost:8080/addStrings
Content-Type: application/json

[
"Str1",
"str2",
"tr3"
]

###
GET http://localhost:8080/strings

###
GET http://localhost:8080/substrings?str=tr
###
