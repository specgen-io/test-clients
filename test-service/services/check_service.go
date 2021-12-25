package services

import (
	"test-service/spec/check"
	"test-service/spec/empty"
)

type CheckService struct{}

func (service *CheckService) CheckEmpty() error {
	return nil
}
func (service *CheckService) CheckForbidden() (*check.CheckForbiddenResponse, error) {
	return &check.CheckForbiddenResponse{Forbidden: &empty.Value}, nil
}

func (service *CheckService) SameOperationName() (*check.SameOperationNameResponse, error) {
	return &check.SameOperationNameResponse{Ok: &empty.Value}, nil
}
