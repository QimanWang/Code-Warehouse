cmake_minimum_required(VERSION 3.8)
project(CS361_numerical_methods)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        CS361_hw/hw_1_1_gcd.cpp
        CS361_hw/hw_1_2_dec_hex.cpp
        CS361_hw/hw_2_1_Fib.cpp
        CS361_hw/hw_2_2_exp_sum_horners_rule.cpp
        CS361_hw/hw_2_3_binomial_coefficent.cpp
        CS361_hw/hw_2_4_bisection.cpp
        CS361_hw/main.cpp
        "cs361_hw doc/23083677_Qiman_wang_cs361_hw2b.docx"
        "misc/23083677_Qiman_Wang_ CS361 midterm1a Sep2017.pdf"
        README.md CS361_hw/hw_5_2_logarithmic_difference.cpp CS361_hw/hw_5_5_call_option.cpp CS361_hw/hw_6_integration.cpp)

add_executable(CS361_numerical_methods ${SOURCE_FILES})