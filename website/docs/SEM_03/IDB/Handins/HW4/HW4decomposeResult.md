```sh
         fd          |   validity    
---------------------+---------------
 Project: id --> pid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: id --> sid | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: id --> sn | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: id --> pn | does not hold
(1 row)

         fd          | validity 
---------------------+----------
 Project: id --> mid | MAY HOLD
(1 row)

         fd         | validity 
--------------------+----------
 Project: id --> mn | MAY HOLD
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: pid --> id | does not hold
(1 row)

          fd          |   validity    
----------------------+---------------
 Project: pid --> sid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: pid --> sn | does not hold
(1 row)

         fd          | validity 
---------------------+----------
 Project: pid --> pn | MAY HOLD
(1 row)

          fd          |   validity    
----------------------+---------------
 Project: pid --> mid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: pid --> mn | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: sid --> id | does not hold
(1 row)

          fd          |   validity    
----------------------+---------------
 Project: sid --> pid | does not hold
(1 row)

         fd          | validity 
---------------------+----------
 Project: sid --> sn | MAY HOLD
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: sid --> pn | does not hold
(1 row)

          fd          |   validity    
----------------------+---------------
 Project: sid --> mid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: sid --> mn | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: sn --> id | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: sn --> pid | does not hold
(1 row)

         fd          | validity 
---------------------+----------
 Project: sn --> sid | MAY HOLD
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: sn --> pn | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: sn --> mid | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: sn --> mn | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: pn --> id | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: pn --> pid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: pn --> sid | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: pn --> sn | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: pn --> mid | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: pn --> mn | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: mid --> id | does not hold
(1 row)

          fd          |   validity    
----------------------+---------------
 Project: mid --> pid | does not hold
(1 row)

          fd          |   validity    
----------------------+---------------
 Project: mid --> sid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: mid --> sn | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: mid --> pn | does not hold
(1 row)

         fd          | validity 
---------------------+----------
 Project: mid --> mn | MAY HOLD
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: mn --> id | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: mn --> pid | does not hold
(1 row)

         fd          |   validity    
---------------------+---------------
 Project: mn --> sid | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: mn --> sn | does not hold
(1 row)

         fd         |   validity    
--------------------+---------------
 Project: mn --> pn | does not hold
(1 row)

         fd          | validity 
---------------------+----------
 Project: mn --> mid | MAY HOLD
(1 row)
```

This leaves us with a list of those that may hold:

```sh
id --> mid
id --> mn

pid --> pn

sid --> sn

sn --> sid

mid --> mn

mn --> mid
```

For `mid+mn` and `sid+sn` have dependencies both ways, we assume `mid+sid` are some sort of ids and use these as keys.

This leaves us with the final list:

```sh
id --> mid

pid --> pn

sid --> sn

mid --> mn
```

That gives us the database scheme:

```sql
Projects(id, pid, sid, mid)
IDtoMID(id, mid)
P(pid, pn)
S(sid, sn)
M(mid, mn)
```
