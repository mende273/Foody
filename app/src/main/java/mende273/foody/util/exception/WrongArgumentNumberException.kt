package mende273.foody.util.exception

class WrongArgumentNumberException :
    Exception(
        "Provided arguments number is greater " +
            "or lower than the specified navArgument list"
    )
