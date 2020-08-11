<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AlterCoDProducts extends AbstractMigration
{

    public function change(): void
    {
        $table = $this->table('products');
        $table->addColumn('disable_cod', 'decimal', ['precision' => 5,'scale'=>2])->update();

    }
}
