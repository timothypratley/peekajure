# Peekajure

<img src="Peekajure.svg" alt="HappyAPI logo" width="200" align="right"/>

A Clojure library for summarizing large values.

## Rationale

Some values are too large to print at the REPL.

Common workarounds are to print just the keys of a map, or the count.

It might be nice to have the computer produce the most informative, yet still compact representation.

Inspectors exist, but don't solve the problem of concise summaries.

## Status

Pre-alpha - please tell me how to do this better.

TODO:

* recursive/nested
* indentation - preserve structure and pprint?
* better logic
* more examples
* extensibility (tmd datasets etc)

## Usage

...

## Contributing

Issues, suggestions and pull requests are welcome.

## Deploying

```
env CLOJARS_USERNAME=username CLOJARS_PASSWORD=clojars-token clojure -T:dev:build build/deploy
```

## License

Copyright © 2024 Timothy Pratley

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
