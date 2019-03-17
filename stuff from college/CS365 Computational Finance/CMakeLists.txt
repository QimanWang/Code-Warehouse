cmake_minimum_required(VERSION 3.8)
project(CS365_computational_finance)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        CS365_hw/hw_1_1_futures_value.cpp
        CS365_hw/hw_1_2_discount_factor.cpp
        CS365_hw/hw_1_3_bond_price_from_yield.cpp
        CS365_hw/hw_1_4_yield_from_bond_price.cpp
        CS365_hw/main.cpp)

add_executable(CS365_computational_finance ${SOURCE_FILES})