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

(define (get-increment-if-match x y)
  (if (equal? x y)
      1
      0
      )
  )

(define (count-occurrences x lst)
  (if (null? lst)
      0
      (+ (get-increment-if-match x (car lst)) (count-occurrences x (cdr lst)))
      )
  )

(define (histogram lst)
  (if (null? lst)
      '()
      (cons (cons (car lst) (count-occurrences (car lst) lst)) (histogram (remove-element (car lst) lst)))
      )
  ) 

(histogram '(#\a #\b #\c #\d #\b #\e #\b))