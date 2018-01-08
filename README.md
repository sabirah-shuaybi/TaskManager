# TaskManager
#### @author: Sabirah Shuaybi
#### @author: Tseki Lhamo
## Motivation:

Research   has   shown   that   multitasking   reduces   one’s   efficiency   and performance   because   the   brain   can   only   focus   on   one   thing   at   a   time. When   an   individual   tries   to   do   two   things   at   once,   their   brain   lacks   the capacity   to   perform   both   tasks   successfully.

Our   motivation   for   this   project   stemmed   from   this   issue   of   multitasking which   can   lead   to   decreased   productivity.   Having   a   lot   of   tasks   can   lead to   distraction   and   anxiety   as   a   person   will   shift   from   one   task   to   another which   reduces   efficiency   in   completing   one   task   at   hand.

## Solution:
Our   solution   was   to   build   an   application   that   would   allow   the   user   to only   focus   on   one   task   (the   task   with   the   closest   due   date)   at   a   time without   displaying   other   tasks   that   may   potentially   cause   a   distraction to   the   person.   We   have   given   the   user   the   option   to   view   all   tasks   on that   have   been   inputted   however   that   would   open   up   as   a   separate window   and   not   on   the   main   display;   again,   to   avoid   distraction.

This   application   will   be   useful   to   people   who   struggle   with   time management   and   handling   their   priorities   since   it   will   direct   them   to what   task/assignment   must   be   done   next   and   keep   them   focused   on   the next   task   at   hand   without   jumping   to   things   that   are   lower   in   priority.
It   will   solve   the   problem   of   time   management   and   hopefully   increase overall   productivity.   The   application   will   also   be   a   little   motivation booster   as   the   users   will   be   able   to   see   the   number   of   tasks   they   have completed.

## Application   Description:
  
### Purpose:
The   purpose   of   our   application   is   to   boost   productivity   by   decreasing distraction   and   multitasking   by   allowing   our   user   to   focus   on   the   most impending   task   on   the   list   (the   task   with   the   nearest   upcoming   due date).

### Tools   and   Resources:
- Swing,   AWT,   util - JDatepicker
- Help   taken   from
- StackOverFlow
- Java   APIs   (Oracle)

### Data   Structures   and   Storage:
The   data   structure   we   utilized   in   our   project   was   a   priority   queue. Having   a   min   priority   queue   as   our   underlying   data   structure   was   a perfect   fit   for   application   and   its   purpose.   Our   application   was   designed to   allow   the   user   to   focus   solely   on   their   next   task,   namely   the   task   with the   highest   priority.   Similarly,   a   PQ   allows   only   the   extraction   of   a   min   or max   (since   we   know   the   max/min   must   be   at   the   root,   whereas   we   do not   know   the   orderings   of   the   rest   of   the   objects   in   the   PQ,   only   the max/min).   This   functionality   allowed   us   to   only   select   the   min   which represents   the   task   of   the   highest   priority.
As   shown   by   the   memory   model   below,   we   are   storing   Task   objects   in the   PQ.   A   task   object   consists   of   a   String   (description   of   the   task),   a   long (second   representation   of   date),   String   (priority   level)   and   separate   int parameters   for   each   date   component,   for   proper   date   formatting  →  month   /   day   /   year)
