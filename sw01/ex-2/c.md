# Macht eine clear Methode auf dem Stack sinn?
eine clear methode könnte den index des stacks direkt auf `STACK_EMPTY_INDEX` setzen, man könnte aber auch pop aufrufen bis der stack leer ist.

clear wäre dann eine effizientere option für das leeren des stacks.

## Lösung
Macht keinen Sinn! C übergibt Parameter als Kopien (call by value) also würde der bestehende Stack gar nicht angefasst sondern es würde ein neuer leerer Stack zurückgegeben werden.
