# datetime-logic

Datetime library for Clojure that uses core.logic.

_What is this?_

This is a Clojure library that allows queries to be run against the Gregorian calendar (and potentially other calendars in the future).

_Why is this?_

Mainly as an excuse for me to play with Clojure's core.logic.  Calendars seem like a great domain to experiment with logic programming.  I hope that this becomes something useful, serving as a practical example of using core.logic in "the real world".

_Where is this going?_

I want to provide an full feature datetime library that answers complex queries about the Gregorian calendar.  I would also love to add more calendars in the future, with the ability to build queries that involve multiple calendars.

## Usage

This is actively being developed so use with caution.  Many simple examples are in the tests.

```clojure
(use 'clojure.core.logic)
(require '[clojure.core.logic.fd :as fd])
(use 'datetime-logic.gregorian)

(run* [month]
  (fd/in month (fd/interval 1 12))
  (day-of-the-weeko 2013 month 13 :friday))
```

Results in _(9 12)_.

## Inspiration

These are the inspirations influencing this project.

- [The Reasoned Schemer](http://mitpress.mit.edu/books/reasoned-schemer) Taught me relational programming
- [Calendrical Calculations](http://emr.cs.iit.edu/home/reingold/calendar-book/third-edition/) Provides an algorithmic explanation of several calendars.
- The many lectures of David Nolen, Daniel Friedman and William Byrd.
- The Clojure/West 2013 miniKanren Confo.

## License

Copyright © 2013 Stephen Sloan

Distributed under the Eclipse Public License, the same as Clojure.
