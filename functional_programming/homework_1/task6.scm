#lang racket

(define (merge-sorted lst1 lst2 pred) 
  (cond
    ((null? lst1) lst2)
    ((null? lst2) lst1)
    ((pred (car lst1) (car lst2)) (cons (car lst1) (merge-sorted (cdr lst1) lst2 pred)))
    (else (cons (car lst2) (merge-sorted lst1 (cdr lst2) pred)))
    )
  )

(define (merge-sort lst pred)
  (if (<= (length lst) 1)
      lst
      (merge-sorted (merge-sort (take lst (quotient (length lst) 2)) pred) (merge-sort (drop lst (quotient (length lst) 2)) pred) pred)
      )
  )

