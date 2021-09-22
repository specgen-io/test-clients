package v2

import "test-service/spec/v2/echo"
import "test-service/spec/v2/models"

type EchoService struct{}

func (service *EchoService) EchoBody(body *models.Message) (*echo.EchoBodyResponse, error) {
	return &echo.EchoBodyResponse{Ok: body}, nil
}
