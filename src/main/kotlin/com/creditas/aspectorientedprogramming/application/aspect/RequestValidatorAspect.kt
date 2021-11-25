package com.creditas.aspectorientedprogramming.application.aspect

import com.creditas.aspectorientedprogramming.application.exception.ProceedingJoinPointNotFoundException
import com.creditas.aspectorientedprogramming.core.services.validator.RequestValidatorService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Component
@Aspect
class RequestValidatorAspect(private val service: RequestValidatorService) {
    companion object {
        const val ANNOTATION_CLASS = "com.creditas.aspectorientedprogramming.application.annotation.RequestValidator"
    }

    @Around("@annotation($ANNOTATION_CLASS)")
    fun validateRequest(joinPoint: ProceedingJoinPoint): Any? {

        val request = extractObjectRequest(joinPoint)

        service.validateRequest(request)

        return joinPoint.proceed()
    }

    private fun extractObjectRequest(joinPoint: ProceedingJoinPoint): Any {
        joinPoint.args.ifEmpty { throw ProceedingJoinPointNotFoundException() }

        val possibleObject = joinPoint.args[0]
        if (possibleObject !is Any) {
            throw ProceedingJoinPointNotFoundException()
        }

        return possibleObject
    }
}