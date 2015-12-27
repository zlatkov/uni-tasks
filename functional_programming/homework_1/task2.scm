#lang racket

(define (get-student-grade student) (list-ref student 2))

(define (get-student-faculty-number student) (list-ref student 0))

(define (get-students-grades students) (map get-student-grade students))

(define (sum-students-grades students) (apply + (get-students-grades students)))
  
(define (average students) (/ (sum-students-grades students) (length students)))

(define (higher-than-average-helper students x) (map get-student-faculty-number (filter (lambda (student) (> (get-student-grade student) x)) students)))

(define (higher-than-average students) (higher-than-average-helper students (average students)))

