package tests

import (
	"gotest.tools/assert"
	"test-client/spec/v2/echo"
	"test-client/spec/v2/models"
	"testing"
)

func Test_Echo_Body_V2(t *testing.T) {
	client := echo.NewClient(service_url)
	expectedMessage := &models.Message{true, "the string"}
	response, err := client.EchoBody(&models.Message{true, "the string"})
	assert.NilError(t, err)
	assert.NilError(t, err, response.Ok)
	assert.DeepEqual(t, expectedMessage, response.Ok)
}
