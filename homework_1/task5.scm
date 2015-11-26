#lang racket

(define (merge-sorted lst1 lst2 pred) 
  (cond
    ((null? lst1) lst2)
    ((null? lst2) lst1)
    ((pred (car lst1) (car lst2)) (cons (car lst1) (merge-sorted (cdr lst1) lst2 pred)))
    (else (cons (car lst2) (merge-sorted lst1 (cdr lst2) pred)))
    )
  )
