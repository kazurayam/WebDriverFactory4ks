import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kms.katalon.core.configuration.RunConfiguration


Map m = RunConfiguration.getExecutionProperties()

Gson gson = new GsonBuilder().setPrettyPrinting().create()

println gson.toJson(m)
