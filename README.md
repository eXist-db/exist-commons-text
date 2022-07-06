# Apache Commons Text functions for eXist-db

This is a library function module for eXist-db that wraps the specifically finding the similarities and distances between strings.

From the function documentation:

```
commons-text:cosine-distance
commons-text:cosine-distance($left as xs:string, $right as xs:string) as xs:double
Measures the cosine distance between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:double : the distance score

commons-text:cosine-similarity
commons-text:cosine-similarity($left as xs:string, $right as xs:string, $delimiter as xs:string?) as xs:double
Measures the cosine similarity between two strings.
Parameters:
$left	The left string
$right	The right string
$delimiter?	The delimiter between the words
Returns:
xs:double : the similarity score

commons-text:hamming-distance
commons-text:hamming-distance($left as xs:string, $right as xs:string) as xs:integer
Measures the hamming distance between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:integer : the distance score

commons-text:jaccard-distance
commons-text:jaccard-distance($left as xs:string, $right as xs:string) as xs:double
Measures the Jaccard distance between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:double : the distance score

commons-text:jaccard-similarity
commons-text:jaccard-similarity($left as xs:string, $right as xs:string) as xs:double
Measures the Jaccard similarity between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:double : the similarity score

commons-text:jaro-winkler-distance
commons-text:jaro-winkler-distance($left as xs:string, $right as xs:string) as xs:double
Measures the Jaro Winkler distance between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:double : the distance score

commons-text:jaro-winkler-similarity
commons-text:jaro-winkler-similarity($left as xs:string, $right as xs:string) as xs:double
Measures the Jaro Winkler similarity between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:double : the similarity score

commons-text:longest-common-subsequence
commons-text:longest-common-subsequence($left as xs:string, $right as xs:string) as xs:integer
Measures the longest common subsequence between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:integer : the longest common subsequence

commons-text:longest-common-subsequence-distance
commons-text:longest-common-subsequence-distance($left as xs:string, $right as xs:string) as xs:integer
Measures the longest common subsequence distance between two strings.
Parameters:
$left	The left string
$right	The right string
Returns:
xs:integer : the longest common subsequence distance
```
