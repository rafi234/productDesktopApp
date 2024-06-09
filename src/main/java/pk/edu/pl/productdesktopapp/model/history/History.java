package pk.edu.pl.productdesktopapp.model.history;

import lombok.Data;

import java.util.List;

@Data
public class History<T> {
    private List<T> history;
}
