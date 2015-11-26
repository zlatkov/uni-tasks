#lang racket

(define (remove-element elem lst)
  (if (null? lst)
      '() 
      (if (equal? elem (car lst))
          (remove-element elem (cdr lst))
          (cons (car lst) (remove-element elem (cdr lst)))
          )
      )
  )
