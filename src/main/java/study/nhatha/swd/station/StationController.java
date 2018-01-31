package study.nhatha.swd.station;

import study.nhatha.swd.generic.AppController;

import java.util.List;

public class StationController implements AppController<Station>{
  private StationRepository stationRepository;

  public StationController(StationRepository stationRepository) {
    this.stationRepository = stationRepository;
  }

  @Override
  public void add(Station item) {

  }

  @Override
  public void add(Iterable<Station> item) {

  }

  @Override
  public void update(Station item) {

  }

  @Override
  public void delete(Station item) {

  }

  @Override
  public Station find(Station item) {
    return null;
  }

  @Override
  public List<Station> all(Station item) {
    return null;
  }
}
