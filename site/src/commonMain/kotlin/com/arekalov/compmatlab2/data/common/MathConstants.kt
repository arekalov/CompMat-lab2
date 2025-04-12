package com.arekalov.compmatlab2.data.common

import com.arekalov.compmatlab2.ui.model.Method
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class MathConstants() {
    companion object {
        val SINGLE_EQUATIONS_LIST = listOf(
            SingleEquation.stub,
            SingleEquation(
                string = "y=2.74x^3-1.93x^2-15.28x-3.72",
                proizv = { x: Double -> 8.22 * x.pow(2) - 3.86 * x - 15.28 },
                phi = { x: Double -> (2.74 * x.pow(3) - 1.93 * x.pow(2) - 3.72) / 15.28 }) { x ->
                2.74 * x.pow(3) - 1.93 * x.pow(2) - 15.28 * x - 3.72
            },
            SingleEquation(
                string = "y=x^3-4.5x^2-9.21x-0.383",
                proizv = { x: Double -> 3 * x.pow(2) - 9 * x - 9.21 },
                phi = { x: Double -> (x.pow(3) - 4.5 * x.pow(2) - 0.383) / 9.21 }) { x ->
                x.pow(3) - 4.5 * x.pow(2) - 9.21 * x - 0.383
            },
            SingleEquation(
                string = "y=1.23*sin(x+1)",
                desmos = "y=1.23\\sin\\left(x+1\\right)",
                proizv = { x: Double -> 1.23 * cos(x + 1) },
                phi = { x: Double -> x }) { x ->
                1.23 * sin(x + 1)
            }
        )

        val SYSTEM_EQUATIONS_FIRST_LIST = listOf<SingleEquation>(
            SingleEquation.stub,
            SingleEquation(
                string = "x+sin(y)=-0.4",
                desmos = "x+\\sin y=-0.4",
                proizv = { _ -> .0 },
                phi = { y -> - sin(y) - 0.4 },
                f = { _ -> .0 },
            ),
            SingleEquation(
                string = "sin(y)+2x=2",
                desmos = "\\sin y+2x=2",
                proizv = { _ -> .0 },
                phi = { y -> (2- sin(y)) / 2 },
                f = { _ -> .0 },
            ),
            SingleEquation(
                string = "sin(x-1)+y=1.5",
                proizv = { _ -> .0 },
                phi = { x -> 1.5 - sin(x - 1) },
                f = { _ -> .0 },
                desmos = "\\sin(x-1)+y=1.5"
            ),
        )

        val SYSTEM_EQUATIONS_SECOND_LIST = listOf<SingleEquation>(
            SingleEquation.stub,
            SingleEquation(
                string = "2y-cos(x-1)=0.7",
                proizv = { x -> (0.7 + cos(x - 1)) / 2 },
                phi = { _ -> .0 },
                f = { _ -> .0 },
                desmos = "2y-\\cos(x-1)=0.7"
            ),
            SingleEquation(
                string = "y+cos(x-1)=0.7",
                proizv = { _ -> .0 },
                phi = { x -> 0.7 - cos(x - 1) },
                f = { _ -> .0 },
                desmos = "y+\\cos(x-1)=0.7"
            ),
            SingleEquation(
                string = "x-sin(y+1)=1",
                proizv = { _ -> .0 },
                phi = { x -> (cos(x - 1) + 0.7) / 2 },
                f = { _ -> .0 },
                desmos = "x-\\sin(y+1)=1"
            ),
        )


        val SINGLE_METHOD_LIST =
            listOf<Method>(Method.Select_method, Method.Newton, Method.SimpleIterations, Method.HalfDivision)

        val SYSTEM_METHOD_LIST = listOf<Method>(Method.Select_method, Method.Newton, Method.SimpleIterations)
    }
}