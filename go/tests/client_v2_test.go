package tests

import (
	"gotest.tools/assert"
	"test-client/spec/v2/echo"
	"test-client/spec/v2/models"
	"testing"
)

func Test_Echo_Body_V2(t *testing.T) {
	client := echo.NewClient(serviceUrl)

	expectedMessage := &models.Message{true, "the string"}
	response, err := client.EchoBody(&models.Message{true, "the string"})

	assert.NilError(t, err)
	assert.NilError(t, err, response)
	assert.DeepEqual(t, expectedMessage, response)
}
