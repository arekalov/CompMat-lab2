package com.arekalov.compmatlab2.data

const val FIRST_EQUATION = "graph1"
const val SECOND_EQUATION = "graph2"

expect fun initGraph()
expect fun clearGraph()
expect fun setExpression(expression: String, expressionId: String)

expect fun jsLog(value: String)